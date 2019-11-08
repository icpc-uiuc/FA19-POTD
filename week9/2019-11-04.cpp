#include<iostream>
#include<cstring>
#include<string>
#include<algorithm>
#include<cmath>
#include<vector>
#include<cstdio>
#include<queue>
using namespace std;

queue<char> qq[2][310][310];
vector<vector<int> > res1;
vector<vector<int> > res2;

void move(int x, int y, int p, int q, int k){
    qq[k][p][q].push(qq[k][x][y].front());
    qq[k][x][y].pop();
    if(k) res2.push_back(vector<int>({p,q,x,y}));
    else res1.push_back(vector<int>({x,y,p,q}));
}

int main() {
    int n, m; cin>>n>>m;
    string s;
    for(int k=0;k<2;++k) for(int i=1;i<=n;++i) for(int j=1;j<=m;++j){
        cin>>s;
        if(!k) reverse(s.begin(), s.end());
        for(char c:s) qq[k][i][j].push(c);
    }
    for(int k=0;k<2;++k) {
        for(int i=2;i<=n;++i) while(!qq[k][i][1].empty()) move(i, 1, 1, 1, k);
        for(int i=2;i<=m;++i) while(!qq[k][1][i].empty()) move(1, i, 1, 1, k);
        while(!qq[k][1][1].empty())
        if(qq[k][1][1].front()-'0'){
            move(1,1,1,2,k);
        } else{
            move(1,1,2,1,k);
        }
        for(int i=2;i<=n;++i) for(int j=2;j<=m;++j){
            while(!qq[k][i][j].empty())
                if(qq[k][i][j].front()-'0'){
                    if(j==2) move(i,j,1,2,k);
                    else{
                        move(i,j,1,j,k);
                        move(1,j,1,2,k);
                    }
                } else{
                    if(i==2) move(i,j,2,1,k);
                    else{
                        move(i,j,i,1,k);
                        move(i,1,2,1,k);
                    }
                }
        }
    }
    cout<<res1.size()+res2.size()<<endl;
    for(int i=0;i<res1.size();++i){
        for(int j=0;j<4;++j) printf("%d ", res1[i][j]);
        printf("\n");
    }
    for(int i=res2.size()-1;i>=0;--i){
        for(int j=0;j<4;++j) printf("%d ", res2[i][j]);
        printf("\n");
    }
    return 0;
}
