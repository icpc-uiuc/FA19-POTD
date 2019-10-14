#include<bits/stdc++.h>
using namespace std;
int main() {
  int n; 
  cin >> n;
  long long k; 
  cin >> k;
  vector<long long> a(n);
  for (int i=0;i<n;i++){
      cin>>a[i];
  }
  sort(a.begin(),a.end());  
  vector<long long> b(n - 1);
  for (int i = 0; i < n - 1; ++i) {
    b[i] = a[i + 1] - a[i];
  } 
  long long ans = a.back() - a[0];
  int l = 0, r = n - 2, p = 1;
  while (l < r) {
    long long take = min(k, (b[l] + b[r]) * p);
    ans -= take / p;
    k -= take;
    ++p, ++l, --r;
  }
  if (l == r) {
    long long take = min(k, b[l] * p);
    ans -= take / p;
    k -= take;
  }
  cout<<ans<<endl;
  
  return 0;
}
