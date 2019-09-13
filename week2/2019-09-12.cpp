#include<bits/stdc++.h>
using namespace std;

int main(){
	double n,m;
	cin>>n>>m;
	double mini=0x3F3F3F;
	for (int i=0;i<n;i++){
		double a,b;
		cin>>a>>b;
		double tmp=(double)(a/b);
		mini=min(mini,tmp);
	}
	cout<<fixed<<setprecision(8)<<(double)(mini*m)<<endl;
	return 0;
}
