#include<bits/stdc++.h>
using namespace std;
 
int main(int argc, const char * argv[]) {
    int n; cin>>n;
    string str; cin>>str;
    string s="";
    int ans=0;
    for(int i=0;i<n;i+=2){
        char c=str[i], d=str[i+1];
        int x=c-'a', y=d-'a';
        if(x+y==1){
            s+=c;
            s+=d;
        } else{
            s+='a';
            s+='b';
            ++ans;
        }
    }
    cout<<ans<<endl;
    cout<<s<<endl;
    return 0;
}
