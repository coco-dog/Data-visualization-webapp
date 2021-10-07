<template>
  <div class="death-container" @dblclick="revertMap">
    <div class="death-chart" ref="Deathrate_ref"></div>
  </div>
</template>

<script>
import axios from 'axios'
// 按需导入州名对应文件的函数
import { getStateInfo } from '@/utils/map_utils'
export default {
  data() {
    return {
      chartInstance: null,
      usaOption: null,
      mapcolor: null,
      mapdata: {}
    }
  },
  // mounted才能获取$ref
  mounted() {
    this.initChart()
    this.getData()
    window.addEventListener('resize', this.screenAdapter)
  },
  methods: {
    // 创建图表
    async initChart() {
      this.chartInstance = this.$echarts.init(this.$refs.Deathrate_ref)
      const ret = await axios.get('http://localhost:8999/static/map/USA.json')
      this.$echarts.registerMap('USA', ret.data, {
        Alaska: { // 把阿拉斯加移到美国主大陆左下方
          left: -131,
          top: 25,
          width: 15
        },
        Hawaii: {
          left: -110, // 夏威夷
          top: 28,
          width: 5
        },
        'Puerto Rico': { // 波多黎各
          left: -76,
          top: 26,
          width: 2
        }
      })
      const initoption = {
        title: {
          text: '美国疫情感染人数',
          left: 'right'
        },
        tooltip: {
          trigger: 'item',
          showDelay: 0,
          transitionDuration: 0.2,
          formatter: function (params) {
            var value = (params.value + '').split('.')
            value = value[0].replace(/(\d{1,3})(?=(?:\d{3})+(?!\d))/g, '$1,')
            return params.seriesName + '<br/>' + params.name + ': ' + value
          }
        },
        visualMap: {
          left: 'right',
          min: 399,
          max: 98298,
          inRange: {
            color: ['#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
          },
          text: ['High', 'Low'], // 文本，默认为数值文本
          calculable: true
        },
        toolbox: {
          show: true,
          // orient: 'vertical',
          left: 'left',
          top: 'top',
          feature: {
            restore: {}
          }
        },
        series: [
          {
            name: 'USA PopEstimates',
            type: 'map',
            roam: true,
            map: 'USA',
            emphasis: {
              label: {
                show: true
              }
            },
            // 文本位置修正
            textFixed: {
              Alaska: [20, -20]
            },
            data: [
              { name: 'Alabama', value: 12376 },
              { name: 'Alaska', value: 399 },
              { name: 'Arizona', value: 14566 },
              { name: 'Arkansas', value: 4923 },
              { name: 'California', value: 83981 },
              { name: 'Colorado', value: 22454 },
              { name: 'Connecticut', value: 38430 },
              { name: 'Delaware', value: 8037 },
              { name: 'District of Columbia', value: 632323 },
              { name: 'Florida', value: 7434 },
              { name: 'Georgia', value: 37214 },
              { name: 'Hawaii', value: 631 },
              { name: 'Idaho', value: 2476 },
              { name: 'Illinois', value: 98298 },
              { name: 'Indiana', value: 29402 },
              { name: 'Iowa', value: 15296 },
              { name: 'Kansas', value: 8488 },
              { name: 'Kentucky', value: 8205 },
              { name: 'Louisiana', value: 35161 },
              { name: 'Maine', value: 1741 },
              { name: 'Maryland', value: 41664 },
              { name: 'Massachusetts', value: 87925 },
              { name: 'Michigan', value: 52337 },
              { name: 'Minnesota', value: 17038 },
              { name: 'Mississippi', value: 11704 },
              { name: 'Missouri', value: 11369 },
              { name: 'Montana', value: 471 },
              { name: 'Nebraska', value: 10846 },
              { name: 'Nevada', value: 7170 },
              { name: 'New Hampshire', value: 3721 },
              { name: 'New Jersey', value: 149037 },
              { name: 'New Mexico', value: 6192 },
              { name: 'New York', value: 357757 },
              { name: 'North Carolina', value: 19762 },
              { name: 'North Dakota', value: 1994 },
              { name: 'Ohio', value: 28953 },
              { name: 'Oklahoma', value: 5489 },
              { name: 'Oregon', value: 3726 },
              { name: 'Pennsylvania', value: 67404 },
              { name: 'Rhode Island', value: 12951 },
              { name: 'South Carolina', value: 9056 },
              { name: 'South Dakota', value: 4085 },
              { name: 'Tennessee', value: 18301 },
              { name: 'Texas', value: 51080 },
              { name: 'Utah', value: 7530 },
              { name: 'Vermont', value: 944 },
              { name: 'Virginia', value: 32145 },
              { name: 'Washington', value: 20063 },
              { name: 'West Virginia', value: 1514 },
              { name: 'Wisconsin', value: 13001 },
              { name: 'Wyoming', value: 776 },
              { name: 'Puerto Rico', value: 2805 }
            ]
          }
        ]
      }
      this.usaOption = initoption

      this.chartInstance.setOption(initoption)
      // 点击美国地图显示对应州的地址
      this.chartInstance.on('click', async(arg) => {
        const stateInfo = getStateInfo(arg.name)
        const statepath = await axios.get('http://localhost:8999/' + stateInfo.path)
        console.log(statepath)
        this.$echarts.registerMap(stateInfo.key, statepath.data)
        const changeOption = {
          title: {
            text: '双击空白部分回到主地图',
            left: 'right'
          },
          series: [{
            type: 'map',
            map: stateInfo.key,
            label: {
              show: true
            }
          }]
        }
        this.chartInstance.setOption(changeOption)
      })
    },
    async getData() {
      // 接口地址,在main.js里面可以调基准地址
      const { data: ret } = await this.$http.get('statemap')
      console.log(ret)
      this.updateChart()
    },
    updateChart() {},
    screenAdapter() {
      const titleFontSize = (this.$refs.Deathrate_ref.offsetWidth / 100) * 3.6
      console.log(titleFontSize)
      const adapteroption = {
        title: {
          textstyle: {
            fontsize: titleFontSize
          }
        },
        tooltip: {
          axisPointer: {
            lineStyle: {
              width: titleFontSize
            }
          },
          series: {
            linewidth: titleFontSize
          }
        }
      }
      this.chartInstance.setOption(adapteroption)
      this.chartInstance.resize()
    },
    revertMap() {
      // 由于option的各个部分可以互相覆盖的,只需要把改变的地方赋值回去就好了。
      const changeoption = {
        title: {
          text: '美国疫情感染人数',
          left: 'right'
        },
        series: [{
          name: 'USA PopEstimates',
          type: 'map',
          roam: true,
          map: 'USA',
          label: {
            show: false
          }
        }]
      }
      this.chartInstance.setOption(changeoption)
    }
  }
}
</script>

<style></style>
