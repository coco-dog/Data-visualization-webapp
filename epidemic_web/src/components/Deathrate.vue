<template>
  <div class="death-container">
    <div class="death-chart" ref="Deathrate_ref"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      chartInstance: null,
      timerId: null, // 定时器
      allData: null
    }
  },
  // mounted才能获取$ref
  mounted() {
    this.initChart()
    this.getData()
    window.addEventListener('resize', this.screenAdapter)
  },
  destroyed() {
    clearInterval(this.timerId)
  },
  methods: {
    // 创建图表
    initChart() {
      this.chartInstance = this.$echarts.init(this.$refs.Deathrate_ref)
      this.chartInstance.on('mouseover', () => {
        clearInterval(this.timerId)
      })
      this.chartInstance.on('mouseout', () => {
        this.startInterval()
      })
    },
    async getData() {
      // 接口地址,在main.js里面可以调基准地址
      const { data: ret } = await this.$http.get('line')
      // 返回数据
      // console.log(ret)
      this.allData = ret
      this.updateChart()
      this.startInterval()
    },
    updateChart() {
      const cfrList = this.allData.cfrlist.map((item) => {
        return (item * 100).toFixed(2)
      })
      // option的静态部分
      const initoption = {
        title: {
          text: '病死率'
        },
        tooltip: {
          trigger: 'axis',
          formatter: '{a}: {c}%<br>日期：{b} '
        },
        legend: {
          data: ['病死率']
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          feature: {
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: this.allData.dateList
        },
        yAxis: {
          type: 'value',
          axisLabel: {
            show: true,
            interval: 'auto',
            formatter: '{value}%'
          }
        },
        series: [
          {
            smooth: true,
            name: '病死率',
            type: 'line',
            data: cfrList
          }
        ]
      }
      this.chartInstance.setOption(initoption)
    },
    screenAdapter() {
      const titleFontSize = this.$refs.Deathrate_ref.offsetWidth / 100 * 3.6
      // 图表宽度大小
      // console.log(titleFontSize)
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
    startInterval () {
      if (this.timerId) {
        clearInterval(this.timerId)
      }
      this.timerId = setInterval(() => {
        this.updateChart()
      }, 3000)
    }
  }
}
</script>

<style>
</style>
