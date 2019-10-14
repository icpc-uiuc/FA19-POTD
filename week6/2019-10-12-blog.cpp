##Solution to yesterday's problem  [Complete Tripartite](https://codeforces.com/problemset/problem/1228/D):

###Problem Analysis:
With the max of n approaching $10^5$, it's likely an $O(n)$ (or $O(nlgn)$) algorithm. One solution is to go through each vertex from 1 to n, and assign it to the first group number not used by its neighbors. For example, vertex one will be labeled group 1; vertex two will belong to group 2 if connected to one, otherwise also group 1. This way, it is guaranteed that "There are no edges with both endpoints in each vertex set", which corresponds to the first two rules. To satisfied the third, we simply check whether the degree of each vertex is equal to the size of the rest two sets since there's both at least and at most one edge between two vertices in different sets.

<spoiler summary="Code(C++)">
```
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
```
</spoiler>

##Today's POTD (Hard): [The Maximum Subtree](https://codeforces.com/problemset/problem/1238/F).

###You can discuss in the comments below!
