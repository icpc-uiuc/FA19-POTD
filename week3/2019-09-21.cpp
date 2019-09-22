#include<bits/stdc++.h>
using namespace std;
int a[100005]; 
int main(){
	int n;
	int k;
	cin>>n>>k;
	for(int i=0;i<n;i++){
		cin>>a[i];
	}	
	for(int i=1;i<=n&&k>i;i++){
		k-=i;
	}
	cout<<a[k-1];
	return 0;
}
