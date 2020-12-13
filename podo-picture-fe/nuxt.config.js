export default {
  ssr: false,

  head: {
    title: 'podo-picture-fe',
    meta: [
      {charset: 'utf-8'},
      {name: 'viewport', content: 'width=device-width, initial-scale=1'},
      {hid: 'description', name: 'description', content: ''}
    ],
    link: [
      {rel: 'icon', type: 'image/x-icon', href: '/favicon.ico'},
      {
        rel: "stylesheet",
        href:
          "https://fonts.googleapis.com/css?family=Noto+Sans+KR:300,400,700&display=swap"
      }
    ]
  },

  css: [],
  plugins :[
   { src: "~plugins/moment.js" },
  ],
  components: true,
  buildModules : [],
  modules : [],
  build : {}
}
