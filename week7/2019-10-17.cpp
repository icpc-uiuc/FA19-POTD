#include <cstdio>

int main(){
	static bool vis[100005];
	int n,i,tmp;
	
	scanf("%d",&n);
	
	int now=n;
	for(i=1;i<=n;i++){
		scanf("%d",&tmp);
		vis[tmp]=true;
		while(vis[now])
			printf("%d ",now--);
		printf("\n");
	}
	
	return 0;
}
