<template>
  <el-table :data="tableData" stripe style="width: 100%">
    <el-table-column prop="lotterytime" label="開獎時間"  />
    <el-table-column prop="lotterynumbers" label="開獎號碼"  />
  </el-table>
</template>

<script>
   export default {    
    data() {
      return {
        pagination: {//分頁
        currentPage: 0,
        pageSize: 10,
        total: 100,
        queryString: null,
      },    
      tableData: [],     
      }  
    },
    //VUE初始化執行
    created() {
      this.findPage();
    },
    methods: {    
      //查询
      findPage() {      
        this.axios.get(`http://localhost:8080/findAll/${this.pagination.currentPage}/${this.pagination.pageSize}`).then((res)=>{                                       
                        this.tableData = res.data;                
            })
      }
    }
  }
</script>