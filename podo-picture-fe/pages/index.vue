<template>
  <div class="wrapper">
    <div class="datetime-container">
      <div class="datetime">
        <div class="date">{{currentDate}}</div>
        <div class="time">{{currentTime}}</div>
      </div>
    </div>
    <div class="picture-container">
      <div class="picture" :class="this.pictureFlag ? 'on' : ''">
        <div v-if="pictureA && pictureA.pictureType === 'IMAGE'" class="img-container">
          <img :src="pictureA.url">
        </div>
        <div v-if="pictureA && pictureA.pictureType === 'VIDEO'" class="video-container">
          <video :src="pictureA.url" muted ref="playerA"/>
        </div>
      </div>

      <div class="picture" :class="!this.pictureFlag ? 'on' : ''">
        <div v-if="pictureB && pictureB.pictureType === 'IMAGE'" class="img-container">
          <img :src="pictureB.url">
        </div>
        <div v-if="pictureB && pictureB.pictureType === 'VIDEO'" class="video-container">
          <video :src="pictureB.url" muted  ref="playerB"/>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
  export default {
    data() {
      return {
        display: true,
        currentDate: "",
        currentTime: "",
        pictureA: undefined,
        pictureB: undefined,
        pictureFlag: true,
        timer: {
          doNext: undefined,
          refreshTime: undefined,
        }
      }
    },
    methods: {
      next() {
        console.log('next');

        if (this.pictureFlag) { // A가 사라지는 상황.
          clearTimeout(this.doNext);
          if(this.pictureB.pictureType === 'VIDEO'){
            this.$refs.playerB.play();
          }
          this.doNext = setTimeout(this.next, this.pictureB.duration * 1000); // B 재생 후, next 호출
          this.getNextPicture(this.pictureA.id).then(p => {
            this.pictureA = p;
            this.pictureFlag = !this.pictureFlag;
          }); // A 갱신
          return;
        }

        if (!this.pictureFlag) { // B가 사라지는 상황.
          clearTimeout(this.doNext);
          if(this.pictureA.pictureType === 'VIDEO'){
            this.$refs.playerA.play();
          }
          this.doNext = setTimeout(this.next, this.pictureA.duration * 1000); // A 재생 후, next 호출
          this.getNextPicture(this.pictureB.id).then(p => {
            this.pictureB = p;
            this.pictureFlag = !this.pictureFlag;
          });  // b 갱신
          return;
        }
      },

      updateCurrentDateTime() {
        this.currentDate = this.$moment().format("YYYY년 MM월 DD일");
        this.currentTime = this.$moment().format("hh:mm");
      },

      async getNextPicture(id) {
        if(id === 3){
          id++;
        }

        return await this.$axios.$get(`/api/picture/next?id=${id}`);
      }

    },
    mounted() {
      this.doNext = setTimeout(this.next, this.pictureA.duration * 1000);
      if(this.pictureA.pictureType ==='VIDEO'){
        this.$refs.playerA.play();
      }
    },
    created() {
      this.updateCurrentDateTime();
      this.refreshTime = setInterval(this.updateCurrentDateTime, 1000);
      this.pictureFlag = true;
    },

    async asyncData({ $axios }){
      const pictureA = await $axios.$get(`/api/picture/next`);
      const pictureB = await $axios.$get(`/api/picture/next?id=1`);
      return{
        pictureA : pictureA,
        pictureB : pictureB
      }
    }
  }
</script>

<style scoped lang="scss">
  .datetime-container {
    position: absolute;
    z-index: 1;
    left: 0;
    right: 0;
    bottom: 0;
    height: 13vh;
    padding-bottom: 2vh;
    background: #00000022;
    display: flex;
    justify-content: flex-end;
    align-items: flex-end;
  }

  .datetime {
    padding-right: 8vw;
    color: white;
    display: flex;
    align-items: baseline;
    justify-content: center;

    .date {
      font-size: 20px;
    }

    .time {
      margin-left: 10px;
      font-size: 80px;
    }
  }

  .picture-container {
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    background: #000000;

    .picture {
      position: absolute;
      left: 0;
      right: 0;
      top: 0;
      bottom: 0;
      opacity: 0;

      &.on {
        opacity: 1;
      }
    }
  }

  .img-container {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100vw;
    height: 100vh;

    img {
      height: 100%;
      max-width: 100%;
    }
  }

  .video-container {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100vw;
    height: 100vh;

    video {
      height: 100%;
      max-width: 100%;
    }
  }
</style>
