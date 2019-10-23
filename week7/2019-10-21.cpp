#include <bits/stdc++.h>
#define ll long long
using namespace std;
const int maxn = 1e5 + 10;
unordered_map<int, int> mp;
int a[maxn], n, m, d[maxn];
ll calc()
{
    ll ans = 0;
    for (int i = m; i; i--){
        int k = a[i] - i - 1;
        d[i] = d[mp[k]] + 1;
        mp[k + 1] = i;
    }
    for (int i = 1; i <= n; i++){
        int k = mp[i];
        int T = min(n, i + m + 1 - d[k]);
        ans += T - i;
    }
    return ans;
}
int main(){
    cin>>n>>m;
    ll ans = n;
    for (int i = 1; i <= m; i++)
        cin>>a[i];
    ans += calc();
    memset(d, 0, sizeof(d));
    mp.clear();
    for (int i = 1; i <= m; i++)
        a[i] = n + 1 - a[i];
    ans += calc();
    if(n==1) ans=0;
    cout << ans;
}
