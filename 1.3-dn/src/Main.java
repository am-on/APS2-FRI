public class Main {

    // keys
    static int [] e = new int[] {8, 4, 10, 2, 6, 9, 1, 3, 5, 7, 0};

    // number of missing nodes for every level
    static int [] l = new int[] {0, 0, 1, 4, 15};

    public static void main(String[] args) {
        System.out.println(child(6, 1));
        System.out.println(parent(10));
    }

    public static int child(int v, int j) {

        if(v >= e.length) return -1;

        int parentIndex = perfectIndex(v);
        int childIndex = parentIndex*2+j;

        int level = (int)Math.floor(Math.log(parentIndex+1)/Math.log(2)) + 1;

        childIndex = realIndex(childIndex, level);

        if (childIndex < e.length && childIndex > 0) {
            return e[childIndex];
        }

        return -1;
    }

    public static int parent(int v) {

        if(v >= e.length) return -1;

        int childIndex = perfectIndex(v);
        int parentIndex = (childIndex-1) / 2;
        int level = (int)Math.floor(Math.log(childIndex+1)/Math.log(2)) - 1;
        parentIndex = realIndex(parentIndex, level);

        if (parentIndex < e.length && parentIndex >= 0) {
            return e[parentIndex];
        }

        return -1;
    }

    public static int realIndex(int perfectIndex, int level) {
        for (int i = 0; i < level; i++) {
            perfectIndex-= l[i];
        }
        return perfectIndex;
    }

    public static int perfectIndex(int v) {
        int rIndex = 0;
        int i = 0;
        int k = 0;

        while (i < l.length && k < v) {
            rIndex += l[i];
            int maxIndex = (int)Math.pow(2,i) * 2 - 2;
            while (k < v && k < maxIndex) {
                rIndex++;
                k++;
            }
            i++;
        }

        return rIndex;
    }
}
