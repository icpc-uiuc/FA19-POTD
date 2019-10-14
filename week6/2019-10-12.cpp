#include<iostream>
#include<cmath>
#include<vector>
#include<cstring>
using namespace std;

vector<int> g[300010];
int s[300010], num[5];

int main(int argc, const char * argv[]) {
    int n, m; cin>>n>>m;
    for(int i=0;i<m;++i){
        int u, v; cin>>u>>v;
        g[u].push_back(v);
        g[v].push_back(u);
    }
    memset(s, 0, sizeof(s));
    memset(num, 0, sizeof(num));
    for(int i=1;i<=n;++i){
        bool b[4]; memset(b, false, sizeof(b));
        for(int j=0;j<g[i].size();++j){
            b[s[g[i][j]]]=true;
        }
        for(int j=1;j<=3;++j) if(!b[j]){ s[i]=j; break;}
        if(!s[i]) { cout<<-1<<endl; return 0;}
        ++num[s[i]];
    }
    for(int i=1;i<=3;++i) if(!num[i]){cout<<-1<<endl; return 0;}
    for(int i=1;i<=n;++i) if(g[i].size()!=n-num[s[i]]){ cout<<-1<<endl; return 0;}
    for(int i=1;i<=n;++i) cout<<s[i]<<' ';
    cout<<endl;
    return 0;
}
