#include<bits/stdc++.h>
using namespace std;

pair<long long, int> stu[7010];
 
int main(int argc, const char * argv[]) {
    int n; cin>>n;
    for(int i=0;i<n;++i) cin>>stu[i].first;
    for(int i=0;i<n;++i) cin>>stu[i].second;
    sort(stu, stu+n);
    int idx=n-1;
    long long sum=0;
    vector<long long> algo;
    while(idx>=0){
        if(idx>0&&(stu[idx].first==stu[idx-1].first)){
            sum+=stu[idx].second+stu[idx-1].second;
            algo.push_back(stu[idx].first);
            idx-=2;
            continue;
        }
        bool b=false;
        for(int i=0;i<algo.size();++i)
            if((stu[idx].first&algo[i])==stu[idx].first){
                b=true;
                sum+=stu[idx].second;
                break;
            }
        --idx;
    }
    cout<<sum<<endl;
    return 0;
}
