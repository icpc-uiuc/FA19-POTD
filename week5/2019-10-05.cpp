#include <cstdio>

typedef long long ll;

const ll mod=1000000007;
ll a[105][105],n,m,ans;

void times(ll a[105][105],ll b[105][105]){
	ll tmp[105][105]={0};
	for(int i=0;i<m;i++)
		for(int j=0;j<m;j++)
			for(int k=0;k<m;k++)
				tmp[i][k]=(tmp[i][k]+a[i][j]*b[j][k])%mod;
	for(int i=0;i<m;i++)
		for(int j=0;j<m;j++)
			a[i][j]=tmp[i][j];
}

void pow(ll a[105][105],ll p){
	ll tmp[105][105]={0};
	for(int i=0;i<m;i++)
		tmp[i][i]=1;
	while(p){
		if(p&1)times(tmp,a);
		times(a,a);
		p>>=1;
	}
	for(int i=0;i<m;i++)
		for(int j=0;j<m;j++)
			a[i][j]=tmp[i][j];
}

int main(){
	scanf("%lld%lld",&n,&m);
	
	a[0][0]=a[0][m-1]=1;
	for(int i=1;i<m;i++)
		a[i][i-1]=1;
	
	pow(a,n-m+1);
	
	for(int i=0;i<m;i++)
		ans=(ans+a[0][i])%mod;
	
	printf("%lld\n",ans);
	
	return 0;
}
