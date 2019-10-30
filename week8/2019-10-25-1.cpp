#include<bits/stdc++.h>
using namespace std;

int a[50];

bitset<1200001> dp[25];
int pre[25][1200001];
bool choose[50];
int main(){
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	int n;
	cin>>n;
	for(int i=0;i<2*n;i++)
		cin>>a[i];
	sort(a,a+n+n);
	dp[0][0]=true;
	pre[0][0]=-1;
	for(int i=2;i<2*n;i++)
		for(int j=n-2;j>=0;j--)
			for(int k=(n-1)*a[2*n-1];k>=0;k--)
				if(dp[j][k]&&!dp[j+1][k+a[i]]){
					dp[j+1][k+a[i]]=true;
					pre[j+1][k+a[i]]=i;
				}
	int sum=0;
	for(int i=2;i<2*n;i++)
		sum+=a[i];
	choose[0]=true;
	for(int i=sum/2;;i--)
		if(dp[n-1][i]){
			int idx=n-1,now=i;
			while(pre[idx][now]!=-1){
				choose[pre[idx][now]]=true;
				now-=a[pre[idx][now]];
				idx--;
			}
			break;
		}
	for(int i=0;i<2*n;i++)
		if(choose[i])
			cout<<a[i]<<' ';
	cout<<endl;
	for(int i=2*n-1;i>=0;i--)
		if(!choose[i])
			cout<<a[i]<<' ';
	cout<<endl;
}
