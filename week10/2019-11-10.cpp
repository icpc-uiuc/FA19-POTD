#include<bits/stdc++.h>
using namespace std;

pair<int,int> e[100001];
int cnt[1001],ecnt[2001];
int color[2001][1001];
int f[1001][1001];

void dfs(int x,int c,int cs){
	if(color[x][c]!=0){
		int it=color[x][c];
		dfs(it,cs^c,cs);
		color[it][cs^c]=x;
		color[x][cs^c]=it;
	}
	else
		color[x][cs^c]=0;
}

int main(){
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	int a,b,m;
	cin>>a>>b>>m;
	int ans=0;
	for(int i=1;i<=m;i++){
		cin>>e[i].first>>e[i].second;
		e[i].second+=a;
		ecnt[e[i].first]++;
		ecnt[e[i].second]++;
	}
	for(int i=1;i<=a+b;i++)
		ans=max(ans,ecnt[i]);
	for(int i=1;i<=m;i++){
		int u=e[i].first,v=e[i].second,c1,c2;
		for(int j=1;j<=ans;j++)
			cnt[j]=0;
		for(int j=1;j<=ans;j++){
			if(color[u][j]!=0){
				cnt[j]+=1;
				if(color[v][j]==0)	c1=j;
			}
			if(color[v][j]!=0){
				cnt[j]+=2;
				if(color[u][j]==0)	c2=j;
			}
		}
		bool ok=false;
		for(int j=1;j<=ans;j++)
			if(!cnt[j]){
				color[u][j]=v;color[v][j]=u;
				ok=true;
				break;
			}
		if(ok)	continue;
		dfs(u,c1,c1^c2);
		color[u][c1]=v;color[v][c1]=u;
	}
	for(int i=1;i<=a;i++)
		for(int j=1;j<=ans;j++)
			if(color[i][j])
				f[i][color[i][j]]=j;
	cout<<ans<<endl;
	for(int i=1;i<=m;i++)
		cout<<f[e[i].first][e[i].second]<<' ';
	cout<<endl;
}
