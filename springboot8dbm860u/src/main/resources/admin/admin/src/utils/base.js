const base = {
    get() {
        return {
            url : "http://localhost:8080/springboot8dbm860u/",
            name: "springboot8dbm860u",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springboot8dbm860u/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "B站数据分析可视化系统"
        } 
    }
}
export default base
