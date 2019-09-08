#include<bits/stdc++.h>
using namespace std;

int n;
string s;
int cnt[26]; 

int main(){
	cin>>n;
	if(n>26) {
		cout<<-1;
	}else{
        cin>>s;
		for(int i=0;i<n;i++){
			cnt[s[i]-'a']++;
		}
		int c=0;
		for(int i=0;i<26;i++){
			if(cnt[i]>0) c++;
		}
		cout<<n-c;
	}
	return 0;
}
