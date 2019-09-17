#include<bits/stdc++.h>
using namespace std;
int n;
int k;
int a[100005]={0};
int t[100005]={0};
int sum[100005]={0};
int main(){
	cin>>n>>k;
	for(int i=1;i<=n;i++){
		cin>>a[i];
	}
	for(int i=1;i<=n;i++){
		cin>>t[i];
	}
	int ans=0;
	for(int i=1;i<=n;i++){
		if(t[i]) ans+=a[i];
		if(!t[i])sum[i]=sum[i-1]+a[i];
		else sum[i]=sum[i-1];
	} 
	int tmp=0;
	for(int i=1;i<=(n-k+1);i++){
		tmp=max(tmp,(sum[i+k-1]-sum[i-1]));
	}
	cout<<tmp+ans;
	return 0;
}

