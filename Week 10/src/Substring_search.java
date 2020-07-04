//子串搜索
//蛮力搜索时间复杂度：
//最好情况：对于每一特定的i在起始位置就与模式串不匹配，在这种情况下只需N-M+1次比较，即O(N)；
//最坏情况：对于某一特定的i,在与模式串比对过程中，前M-1项皆匹配成功，第M项比对失败，也就是说文本串中的每个字符都与模式串中的每一个字符比对一次，在这种情况下需要进行（N-M+1）* M次比较，即O(N*M)；


public class Substring_search {
    //蛮力搜索(缓冲区方式)
    public static int bruteSearchAreaWay(String pat, String txt){
        int M = pat.length();
        int N = txt.length();
        for (int i = 0; i <= N - M; i++){
            int j;
            for (j = 0; j < M; j++){
                if (txt.charAt(i + j) != pat.charAt(j)){
                    break;
                }
            }
            if (j == M){
                return i;
            }
        }
        return N;   // not found
    }
    public static int bruteSearchOtherway(String pat, String txt){
        int i,M = pat.length();
        int j,N = txt.length();
        for (i = 0, j = 0; i < N && j < M; i++){
            //此时进入循环时，i已经+1，即上一次匹配开始字符的下一个字符
            if (txt.charAt(i) == pat.charAt(j)){
                j++;
            }
            else {
                i = i - j;      //backup
                j = 0;
            }
        }
        if (j == M){
            return i - M;       //found
        }
        else {
            return N;       //not found
        }
    }
    public static void main(String[] args){
        System.out.println(bruteSearchOtherway("sssss","rsssssrssss"));
    }
}