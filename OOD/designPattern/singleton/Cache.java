package OOD.designPattern.singleton;
// 在initialize非常expensive 和 全局唯一逻辑的情况下 使用singleton
class Cache {
    private static Cache cacheInstance;

    private Cache () {

    }

//    public static Cache getInstance() {
//        synchronized (this) {
//            if (cacheInstance == null) {
//                cacheInstance = new Cache();
//            }
//            return cacheInstance;
//        }
//    }

    /**
     * Dependency Injection
     * Key- annotation and type
     * value
     *
     */
}
