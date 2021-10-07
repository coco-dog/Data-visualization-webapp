<template>
  <div class="screen-container">
    <!-- 头部 -->
    <header>
      <h1 class="h1">美国疫情信息查询及趋势预测</h1>
    </header>
    <!-- 页面主体盒子 -->
    <section class="mainbox">
      <!-- 左侧 -->
      <div class="column">
        <div class="panel bar">
          <div class="chart">
            <deathrate></deathrate>
          </div>
        </div>
        <div class="panel2">
          <div class="chart">
            <search></search>
          </div>
        </div>
      </div>
      <!-- 中间 -->
      <div class="column">
        <div class="no">
          <div class="no-hd">
            <ul>
              <li>数据</li>
              <ol></ol>
              <li>分析</li>
            </ul>
          </div>
          <div class="no-bd">
            <ul>
              <li></li>
              <ol></ol>
              <li></li>
            </ul>
          </div>
        </div>
        <div class="map">
          <div id="map" class="chart">
            <usamap></usamap>
          </div>
        </div>
      </div>
      <!-- 右侧 -->
      <div class="column">
         <div class="no">
          <div class="no-hd">
            <ul>
              <li>{{ cease }}</li>
              <ol>|</ol>
              <li>{{ death }}</li>
            </ul>
          </div>
          <div class="no-bd">
            <ul>
              <li>美国累计感染</li>
              <ol></ol>
              <li>美国累计死亡</li>
            </ul>
          </div>
        </div>
        <div class="panel3">
          <div class=searchinput>
            <el-autocomplete
            class="inline-input"
            v-model="state"
            :fetch-suggestions="querySearch"
            placeholder="请选择要查询的州"
            @select="handleSelect"
    ></el-autocomplete>
    </div>
          <el-table :data="tableData" style="width: 100%" class="table">
            <el-table-column prop="county" label="地区">
            </el-table-column>
            <el-table-column prop="cases" label="累计感染">
            </el-table-column>
            <el-table-column prop="deaths" label="累计死亡"> </el-table-column>
          </el-table>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import '../../public/static/lib/flexible.js'
import Deathrate from '../components/Deathrate'
import Search from '../components/Search'
import Usamap from '../components/Usamap'
export default {
  components: {
    deathrate: Deathrate,
    search: Search,
    usamap: Usamap
  },
  data() {
    return {
      death: null,
      cease: null,
      state: '',
      restaurants: [],
      tableData: [
        {
          county: '',
          cases: '',
          deaths: ''
        }
      ]
    }
  },
  mounted() {
    this.getData()
  },
  methods: {
    async getData() {
      const { data: res } = await this.$http.get('allstate')
      this.restaurants = res.map(item => ({ value: item }))
      // 接口地址,在main.js里面可以调基准地址
      const { data: ret } = await this.$http.get('total ')
      // console.log(ret)
      this.death = ret.deaths
      this.cease = ret.cases
      const statePath = '/detaildata?state=New York'
      // console.log(statePath)
      const { data: tbData } = await this.$http.get(statePath)
      this.tableData = tbData
      // console.log(this.tableData)
    },
    querySearch(queryString, cb) {
      var restaurants = this.restaurants
      var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants
      // 调用 callback 返回建议列表的数据
      cb(results)
    },
    createFilter(queryString) {
      return (restaurant) => {
        return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0)
      }
    },
    async handleSelect(item) {
      // console.log(item)
      const stateName = item.value
      const statePath = '/detaildata?state=' + stateName
      // console.log(statePath)
      const { data: ret } = await this.$http.get(statePath)
      this.tableData = ret
      // console.log(this.tableData)
    }
  }
}
</script>

<style>
* {
  box-sizing: border-box;
}
.screen-container {
  margin: 0;
  line-height: 1.15;
  min-width: 1024px;
  max-width: 1920px;
  overflow: hidden;
}
li {
  list-style: none;
}
/* 声明字体*/
@font-face {
  font-family: electronicFont;
  src: url(../../public/static/font/DS-DIGIT.TTF);
}
header {
  height: 1rem;
  background-color: white;
  background-size: 100% 100%;
  position: relative;
}
header h1 {
  font-size: 0.7rem;
  color: #f86551;
  line-height: 1rem;
  text-align: center;
  margin: 0px;
}
.mainbox {
  display: flex;
  min-width: 1024px;
  max-width: 1920px;
  margin: 0 auto;
  padding: 0.125rem;
  padding-bottom:.125rem;
}
.mainbox .column {
  flex: 3;
}
.mainbox .column:nth-child(2) {
  flex: 5;
  margin: 0 0.125rem 0.1875rem;
}
.mainbox .panel {
  position: relative;
  height: 4.75rem;
  padding: 0 0.1875rem 0.5rem;
  margin-bottom: 0.1875rem;
  background: white;
}
.mainbox .panel .chart {
  height: 100%;
}
.mainbox .panel2 {
  position: relative;
  height: 8rem;
  padding: 0 0.1875rem 0rem;
  background: white;
}
.mainbox .panel3 {
  position: relative;
  height: 9.5rem;
  padding: 0 0.1875rem 0.5rem;
  margin-bottom: 0.25rem;
  background: white;
  overflow: auto;
}
.mainbox .panel2 .chart {
  height: 100%;
}
.no {
  padding-top: 0.125rem;
  background: white;
}
.no .no-hd {
  position: relative;
}

.no .no-hd ul {
  display: flex;
  padding: .20rem;
  margin-bottom: 0%;
}
.no .no-bd ul {
  display: flex;
  padding: .20rem;
  margin-bottom: 0%;
}
.no .no-hd ul ol {
  flex: 1px;
  height: 1rem;
  font-size: 0.875rem;
  color: #f86551;
  text-align: center;
  font-family: electronicFont;
  position: relative;
  padding: 0;
}
.no .no-hd ul li {
  flex: 1px;
  height: 1rem;
  font-size: 0.875rem;
  color: #f86551;
  text-align: center;
  font-family: electronicFont;
  position: relative;
}
.no .no-hd ul ol{
  height: 1rem;
  font-size: 0.875rem;
  color: #f86551;
  text-align: center;
  font-family: electronicFont;
  position: relative;
}
.no .no-bd ul {
  display: flex;
  background: white;
  margin: 0.125rem;
  padding: 0%;
}
.no .no-bd ul ol {
  flex: 1px;
  height: 1rem;
  font-size: 0.875rem;
  color: #f86551;
  text-align: center;
  font-family: electronicFont;
  position: relative;
  padding: 0;
}
.no .no-bd ul li {
  flex: 1;
  text-align: center;
  color: #000000;
  font-size: 0.34rem;
  height: 0.5rem;
  line-height: 0.5rem;
  padding-top: 0.1rem;
}
.map {
  position: relative;
  height: 9.5rem;
  overflow: hidden;
  background: white;
  padding: 0%;
}
.map .chart {
  position: relative;
  width: 100%;
  height: 100%;
}
.panel3 .el-table {
  background: white;
}

/* 约束屏幕尺寸 */
@media screen and (max-width: 1024px) {
  html {
    font-size: 42px !important;
  }
}
@media screen and (min-width: 1920px) {
  html {
    font-size: 80px !important;
  }
}
</style>
