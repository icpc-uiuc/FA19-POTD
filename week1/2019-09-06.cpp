#include<bits/stdc++.h>
using namespace std;

int p[200000],q[200000];

int main(){
	int n,k;
	cin>>n>>k;
	for(int i=0;i<n;i++)
		cin>>p[i];
	for(int i=0;i<n;i++)
		cin>>q[i];
	set<int> occur;
	vector<int> pos;
	for(int i=0;i<n;i++){
		if(occur.find(p[i])==occur.end())
			occur.insert(p[i]);
		else
			occur.erase(p[i]);
		if(occur.find(q[i])==occur.end())
			occur.insert(q[i]);
		else
			occur.erase(q[i]);
		if(occur.empty())
			pos.emplace_back(i);
	}
	string ans;
	ans.resize(n);
	if(pos.size()<k)
		cout<<"NO"<<endl;
	else{
		cout<<"YES"<<endl;
		for(int i=0,j=0;i<n;i++){
			ans[p[i]-1]=min('a'+j,'z'+0);
			if(i==pos[j])	j++;
		}
		cout<<ans<<endl;
	}
}
