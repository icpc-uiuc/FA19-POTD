#include<bits/stdc++.h>
using namespace std;

bool impossible[100001];

int main(){
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	int n;
	cin>>n;
	for(int i=1,p,c;i<=n;i++){
		cin>>p>>c;
		if(c==0){
			impossible[i]=true;
			if(p!=-1)
				impossible[p]=true;
		}
	}
	bool exist=false;
	for(int i=1;i<=n;i++)
		if(!impossible[i]){
			exist=true;
			cout<<i<<' ';
		}
	if(!exist)	cout<<-1;
	cout<<endl;
}
