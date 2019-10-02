#include<bits/stdc++.h>
using namespace std;
int main(){
    int n;
    int k;
    cin>>n>>k;
    priority_queue<int> pq;
    int t=(int)log2(n);
    while(n){
        int tmp=pow(2,t);
        int s=n/tmp;
        for(int i=0;i<s;i++){
            pq.push(tmp);
        }
        t--;
        n-=(s*tmp);
    }
    if(pq.size()>k){
        cout<<"NO"<<endl;
        return 0;
    }
    while(pq.size()<k){
        int p=pq.top();
        pq.pop();
        if(p>1){
            pq.push(p/2);
            pq.push(p/2);
        }else{
            break;
        }
    }
    if(pq.size()<k){
        cout<<"NO"<<endl;
        return 0;
    }
    stack<int> st;
    int len=pq.size();
    for(int i=0;i<len;i++){
        int p=pq.top();
        pq.pop();
        st.push(p);
    }

    cout<<"YES"<<endl;
    len=st.size();
    for(int i=0;i<len;i++){
        cout<<st.top()<<" ";
        st.pop();
    }
}
