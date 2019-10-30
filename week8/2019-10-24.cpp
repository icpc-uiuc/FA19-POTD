#include<bits/stdc++.h>
using namespace std;

typedef long long ll;
const int mod = 1000000007;

ll fastpow(ll a, ll b) {
    if(b==0) return 1;
    return b%2==0 ? fastpow(a*a%mod, b/2) : fastpow(a*a%mod, b/2)*a%mod;
}

int main() {
    ll n, m; cin>>n>>m;
    cout<<fastpow(fastpow(2, m)-1, n)<<endl;
    return 0;
}
