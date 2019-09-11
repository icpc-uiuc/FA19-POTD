#include<bits/stdc++.h>
using namespace std;

const int MAXN = 4e5+10;
const int ALPHASIZE = 26;

vector<pair<int,int>> songs[MAXN];//(Transition,nextnode)
vector<pair<int,int>> queries[MAXN];//(index in Trie,query number)
vector<int> FailTree[MAXN];
int fail[MAXN];//fail link for automata
int ans[MAXN];
int trans[MAXN][ALPHASIZE];
int time_in[MAXN],time_out[MAXN];//DFS order
int BIT[MAXN];
//The edge of the Trie before build
//the transition for the automata after build
int Trieidx;
int dfs_time;

int insert(string& pattern){
	//insert the string into the Trie
	//return the index of the node in the Trie
	int now=0;
	for(int i=0;i<pattern.size();i++)
		if(trans[now][pattern[i]-'a'])
			now=trans[now][pattern[i]-'a'];
		else
			now=trans[now][pattern[i]-'a']=++Trieidx;
	return now;
}

//build the automata and the fail tree
void build(){
	//build the automata
	int now=0;
	queue<int> q;
	for(int i=0;i<ALPHASIZE;i++)
		if(trans[now][i])
			q.push(trans[now][i]);
	while(!q.empty()){
		now=q.front();q.pop();
		for(int i=0;i<ALPHASIZE;i++){
			if(trans[now][i]){
				fail[trans[now][i]]=trans[fail[now]][i];
				q.push(trans[now][i]);
			}
			else
				trans[now][i]=trans[fail[now]][i];
		}
	}
	//build the fail tree
	for(int i=1;i<=Trieidx;i++)
		FailTree[fail[i]].emplace_back(i);
}

//get the dfs order of the fail tree
void dfs_order(int now){
	time_in[now]=++dfs_time;
	for(int nxt:FailTree[now])
		dfs_order(nxt);
	time_out[now]=dfs_time;
}

//Binary Indexed Tree
void add(int pos,int val){
	for(;pos<=dfs_time;pos+=pos&-pos)
		BIT[pos]+=val;
}

int sum(int pos){
	int ret=0;
	for(;pos;pos-=pos&-pos)
		ret+=BIT[pos];
	return ret;
}

void dfs_solve_query(int Trie_pos,int song_pos){
	//Given a node in a fail tree, if there is another node such that
	//it is the ancestor of the given node, then the string represented
	//in the Trie by this node is a suffix of the string represented in
	//the Trie by the given node.
	add(time_in[Trie_pos],1);
	for(auto it:queries[song_pos])
		ans[it.second]=sum(time_out[it.first])-sum(time_in[it.first]-1);
	for(auto it:songs[song_pos])
		dfs_solve_query(trans[Trie_pos][it.first],it.second);
	add(time_in[Trie_pos],-1);
}

int main(){
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	int n;
	cin>>n;
	//We read the input of the description of the album
	//and build a tree for the songs. Every nodes except the root
	//represents a song. An directed edge in the tree is represented as
	//a pair. The first term is the transistion between nodes and the
	//second term is the index of the next node. Therefore, a path from
	//the root to a node is actually the name of the song. The root's index
	//is 0.
	for(int i=1,type,parent;i<=n;i++){
		char c;
		cin>>type;
		if(type==1){
			parent=0;
			cin>>c;
		}
		else
			cin>>parent>>c;
		songs[parent].emplace_back(make_pair(c-'a',i));
		//shift the letters to 0 ~ 25
	}
	//Build the AC automata or Trie graph for the queries
	int m;
	cin>>m;
	for(int i=1,which;i<=m;i++){
		string pattern;
		cin>>which>>pattern;
		queries[which].emplace_back(make_pair(insert(pattern),i));
	}
	build();//build the automata and fail tree
	dfs_order(0);//get the dfs order of fail tree
	dfs_solve_query(0,0);//solve the queries
	for(int i=1;i<=m;i++)
		cout<<ans[i]<<'\n';
}
