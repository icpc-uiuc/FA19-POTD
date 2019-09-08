#include<iostream>
#include<queue>
using namespace std;

string cells[2000];
int left_move_counter[2000][2000];

int main(){
	int n,m;
	int start_r,start_c;
	int r_limit,l_limit;
	int now_r,now_c;
	cin>>n>>m;
	cin>>start_r>>start_c;
	start_r--;start_c--;
	//Since we want to use string to store the data
	//and the string is 0-index, we need to shift the coordinates
	cin>>l_limit>>r_limit;
	for(int i=0;i<n;i++)
		cin>>cells[i];

	//We use -1 to denote that we haven't visited this cell in bfs
	//Whenever the left_move is opimized, the right_move is optimized as well.
	//Therefore, we just need to record one of them, here we choose to record left
	for(int i=0;i<n;i++)
		for(int j=0;j<m;j++)
			left_move_counter[i][j]=-1;
	
	queue<pair<int,int> > high_priority,low_priority;
	//We are going to use bfs to solve this problem
	//We need to put an initial cell into the queue
	high_priority.push(make_pair(start_r,start_c));
	left_move_counter[start_r][start_c]=0;//visit the initial cell;
	while(!(high_priority.empty()&&low_priority.empty())){
		//we want to flood-fill the cell, but with high priority
		//for moving up, down and left since we can freely move in
		//these three directions without incrementing the left_move_counter
		if(!high_priority.empty()){
			now_r=high_priority.front().first;
			now_c=high_priority.front().second;
			high_priority.pop();
		}
		else{
			now_r=low_priority.front().first;
			now_c=low_priority.front().second;
			low_priority.pop();
		}
		//try to move up, down, or right
		//the new state has high priority
		if((now_r-1>=0)&&left_move_counter[now_r-1][now_c]==-1&&cells[now_r-1][now_c]!='*'){
			//not out of border and we haven't visited it
			//also it's not an obstacle
			left_move_counter[now_r-1][now_c]=left_move_counter[now_r][now_c];
			high_priority.push(make_pair(now_r-1,now_c));
		}
		if((now_r+1<n)&&left_move_counter[now_r+1][now_c]==-1&&cells[now_r+1][now_c]!='*'){
			//not out of border and we haven't visited it
			//also it's not an obstacle
			left_move_counter[now_r+1][now_c]=left_move_counter[now_r][now_c];
			high_priority.push(make_pair(now_r+1,now_c));
		}
		if((now_c+1<m)&&left_move_counter[now_r][now_c+1]==-1&&cells[now_r][now_c+1]!='*'){
			//not out of border and we haven't visited it
			//also it's not an obstacle
			left_move_counter[now_r][now_c+1]=left_move_counter[now_r][now_c];
			low_priority.push(make_pair(now_r,now_c+1));
		}
		//try to move left
		//the new state has low prioddrity
		if((now_c-1>=0)&&left_move_counter[now_r][now_c-1]==-1&&cells[now_r][now_c-1]!='*'){
			//not out of border and we haven't visited it
			//also it's not an obstacle
			left_move_counter[now_r][now_c-1]=left_move_counter[now_r][now_c]+1;
			low_priority.push(make_pair(now_r,now_c-1));
		}
	}
	int ans=0;
	for(int i=0;i<n;i++)
		for(int j=0;j<m;j++){
			int now=left_move_counter[i][j];
			if((now!=-1)&&(now<=l_limit)&&(j-start_c+now<=r_limit))
				ans++;
			//if we have visited this cell and the left_move and right_move
			//is inside the limitation
			//We know how many times we move right since the difference
			//between left_move and right_move should be a constant,
			//which is the offset of the column
		}
	cout<<ans<<endl;
}
