const base = {
    get() {
        return {
            url : "http://localhost:8080/django8dbm860u/",
            name: "django8dbm860u",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "B站数据分析可视化系统"
        } 
    }
}
export default base
