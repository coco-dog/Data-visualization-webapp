const name = {
  Alabama: 'AL',

  Alaska: 'AK',

  Arizona: 'AZ',

  Arkansas: 'AR',

  California: 'CA',

  Colorado: 'CO',

  Connecticut: 'CT',

  Delaware: 'DE',

  Florida: 'FL',

  Georgia: 'GA',

  Hawaii: 'HI',

  Idaho: 'ID',

  Illinois: 'IL',

  Indiana: 'IN',

  Iowa: 'IA',

  Kansas: 'KS',

  Louisiana: 'LA',

  Maine: 'ME',

  Massachusetts: 'MA',

  Maryland: 'MD',

  Michigan: 'MI',

  Minnesota: 'MN',

  Mississippi: 'MS',

  Missouri: 'MO',

  Montana: 'MT',

  Nevada: 'NV',

  'New hampshise': 'NH',

  'New Jersey': 'NJ',

  'New Mexico': 'NM',

  'New York': 'NY',

  'North Carolina': 'NC',

  'North Dakota': 'ND',

  Ohio: 'OH',

  Oklahoma: 'OK',

  Oregon: 'OR',

  Pennsylvania: 'PA',

  Rhode: 'RI',

  'South Carolina': 'SC',

  'South Dakota': 'SD',

  Tennessee: 'TN',

  Texas: 'TX',

  Utah: 'UT',

  Vermont: 'VT',

  Virginia: 'VA',

  Washington: 'WA',

  'West Virginia': 'WV',

  Wisconsin: 'WI',

  Wyoming: 'WY'
}
export function getStateInfo(arg) {
  const path = `static/map/USA/${name[arg]}.geo.json`
  return {
    key: name[arg],
    path: path
  }
}

// Alabama                    亚拉巴马州 AL

// Alaska                     阿拉斯加州 AK

// Arizona                    亚利桑那州 AZ

// Arkansas                   阿肯色州  AR

// California                 加利福尼亚州 CA

// Colorado                   科罗拉多州 CO

// Connecticut                康涅狄格州 CT

// Delaware                   特拉华州 DE

// Florida                    佛罗里达州 FL

// Georgia                    佐治亚州 GA

// Hawaii                     夏威夷 HI

// Idaho                      爱达荷州 ID

// Illinois                   伊利诺伊州 IL

// Indiana                    印第安纳州 IN

// Iowa                       艾奥瓦(衣阿华)州 IA

// Kansas                     堪萨斯州 KS

// Louisiana                  路易斯安那州 LA

// Maine                      缅因州 ME

// Massachusetts              马塞诸塞州(麻省) MA

// Maryland                   马里兰州 MD

// Michigan                   密歇根(密执安)州 MI

// Minnesota                  明尼苏达州 MN

// Mississippi                密西西比州 MS

// Missouri                   密苏里州 MO

// Montana                    蒙大拿州 MT

// Nevada                     内华达州 NV

// New hampshise              新罕布什尔州 NH

// New Jersey                 新泽西州 NJ

// New Mexico                 新墨西哥州 NM

// New York                   纽约州 NY

// North Carolina             北卡罗来州 NC

// North Dakota               北达科他州 ND

// Ohio                       俄亥俄州 OH

// Oklahoma                   俄克拉何马州 OK

// Oregon                     俄勒冈州 OR

// Pennsylvania               宾夕法尼亚州 PA

// Rhode                      罗得岛 RI

// South Carolina             南卡罗来州 SC

// South Dakota               南达科他州 SD

// Tennessee                  田纳西州 TN

// Texas                      得克萨斯州 TX

// Utah                       犹他州 UT

// Vermont                    佛蒙特州 VT

// Virginia                   弗吉尼亚州 VA

// Washington                 华盛顿州 WA

// West Virginia              西弗吉尼亚州 WV

// Wisconsin                  威斯康星州 WI

// Wyoming                    怀俄明州 WY
