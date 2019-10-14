#include <iostream>
#include <cstdio>

using std::min;

int main(){
	static int a[505][505];
	int n,m,i,j,sum=0;
	
	scanf("%d%d",&n,&m);
	for(i=1;i<=n;i++)
		for(j=1;j<=m;j++){
			scanf("%d",a[i]+j);
			sum+=a[i][j];
		}
	
	for(i=n;i>=1;i--)
		for(j=m;j>=1;j--)
			if(!a[i][j])
				sum+=a[i][j]=min(a[i+1][j],a[i][j+1])-1;
			else if(i!=n && a[i][j]>=a[i+1][j] || j!=m && a[i][j]>=a[i][j+1]){
				printf("-1\n");
				return 0;
			}
	
	printf("%d\n",sum);
	
	return 0; 
}
