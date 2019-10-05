#include<bits/stdc++.h>
using namespace std;
struct nod{
    int x;
    int num;
};
nod nods[1005];
bool cmp(nod a,nod b){
    if(a.x==b.x) return a.num<b.num;
    return a.x>b.x;
}
int main(){
    int n;
    cin>>n;
    int cnt=0;
    for(int i=0;i<n;i++){
        cin>>nods[i].x;
        nods[i].num=i;
    }
    sort(nods,nods+n,cmp);
    int shoot=0;
    for(int i=0;i<n;i++){
        shoot+=(cnt*nods[i].x+1);
        cnt++;
    }
    cout<<shoot<<endl;
    for(int i=0;i<n;i++){
        cout<<nods[i].num+1<<" ";
    }
    return 0;
}
