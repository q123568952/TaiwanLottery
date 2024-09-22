<template>  

  <PageSkele/>

</template>
<script>
import PageSkele from './components/PageSkele.vue';

export default {
  name: 'app',
  components: {     
    PageSkele   
  },
  created () {
    this.axios
      .get('http://localhost:8080/test/1/2',{
        headers: {
       "Access-Control-Allow-Origin": "*",
          "Access-Control-Allow-Methods": "GET, POST, PATCH, PUT, DELETE, OPTIONS",
          "Access-Control-Allow-Headers": "Origin, Content-Type, X-Auth-Token"
      }})
      .then((response) => {

        console.log(response.data)        
        let dataa = JSON.parse(response.data)
        console.log(dataa)        
      })
      .catch((error) => {
        console.log(error)
      })
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50; 
}
</style>
