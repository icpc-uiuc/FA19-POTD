#include<bits/stdc++.h>
#define IOS std::ios::sync_with_stdio(false); std::cin.tie(0);std::cout.tie(0);
#define endl "\n" 

const long long mod =1000000007;
const long long mul =500000004;
using namespace std;

long long quickPower(long long a, long long b,long long mod){
    long long ans = 1, base = a;
    while(b > 0)
    {
        if(b & 1){
        	ans *= base;
        	ans%=mod;
		}
        base *= base;
        base %=mod;
        b >>= 1;
    }
    return ans%mod;
}
long long s[5001][5001];
void ini(int k){
	s[0][0]=1;
	for(int i=1;i<=k;i++)
		for(int j=1;j<=i;j++)
			s[i][j]=((long long)j*s[i-1][j]+s[i-1][j-1])%mod;
}
int main(){
	IOS;
	long long n,k;
	cin>>n>>k;
	ini(k);
	long long ans=0;
	long long x=1;
	long long bit=quickPower(2ll,n,mod);
	for(int i=1;i<=k;i++){
		x=(long long)x*(n-i+1)%mod;
		bit=(long long)bit*mul%mod;
		ans=((long long)s[k][i]*x%mod*bit+ans)%mod;
	}
		
	cout<<ans<<endl;
	
	return 0;
}
