#include<bits/stdc++.h>
using namespace std;

const int N = 1e5+10;
long long int rnd[N<<1],node[N],hsh[N];
vector<int> edge[N];
int cnt[N<<1];
int sz,idcnt,ans,whichnode;
map<long long int,int> id;

void modify(int x,int val){
	if(val==-1)
		sz-=(--cnt[x]==0);
	else
		sz+=(cnt[x]++==0);
}

long long int myrand(){
	return ((long long int)rand()<<32)|((long long int)rand()<<16)|(long long int)rand();
}

int getid(long long int x){
	if(id.find(x)==id.end())
		return id[x]=++idcnt;
	else
		return id[x];
}

void dfs(int x,int p){
	for(int it:edge[x]){
		if(it==p)	continue;
		dfs(it,x);
		hsh[x]+=rnd[node[it]];
	}
	node[x]=getid(hsh[x]);
	modify(node[x],1);
}

void dfs2(int x,int p){
	if(p!=0){
		modify(node[p],-1);
		modify(node[x],-1);
		hsh[p]-=rnd[node[x]];
		node[p]=getid(hsh[p]);
		hsh[x]+=rnd[node[p]];
		node[x]=getid(hsh[x]);
		modify(node[p],1);
		modify(node[x],1);
		if(ans<sz){
			ans=sz;
			whichnode=x;
		}
	}
	for(int it:edge[x])
		if(it!=p)
			dfs2(it,x);
	if(p!=0){
		modify(node[p],-1);
		modify(node[x],-1);
		hsh[x]-=rnd[node[p]];
		node[x]=getid(hsh[x]);
		hsh[p]+=rnd[node[x]];
		node[p]=getid(hsh[p]);
		modify(node[p],1);
		modify(node[x],1);
	}
}

int main(){
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	int n;
	cin>>n;
	for(int i=0;i<=(n<<1);i++)
		rnd[i]=myrand();
	for(int i=1,x,y;i<n;i++){
		cin>>x>>y;
		edge[x].emplace_back(y);
		edge[y].emplace_back(x);
	}
	dfs(1,0);
	ans=sz;
	whichnode=1;
	dfs2(1,0);
	cout<<whichnode<<endl;
}
