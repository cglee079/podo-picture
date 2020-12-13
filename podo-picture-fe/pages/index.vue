<template>
  <div class="wrapper">
    <div class="time-container">
      <div class="time">
        {{$moment().format("hh:mm")}}
      </div>
    </div>
    <div>
      <div class="img-container">
        <img src="http://192.168.50.2:40080/1.JPG">
      </div>
      <div class="img-container" :class="display === true ? 'on' : ''">
        <img src="http://192.168.50.2:40080/2.JPG">
      </div>
      <div class="img-container" :class="display === false ? 'on' : ''">
        <img src="http://192.168.50.2:40080/3.JPG">
      </div>
    </div>

  </div>
</template>

<script>
  export default {
    name: 'App',
    data() {
      return {
        display: true,
        time: "19:16"
      }
    },
    components: {},
    created() {
      const timer = setInterval(() => this.display = !this.display, 10000);
      timer.valueOf();
    }
  }
</script>

<style scoped>
  .time-container {
    position: absolute;
    z-index: 1;
    padding-right: 8vw;
    left : 0;
    right: 0;
    bottom: 0;
    background: #00000077;
    display: flex;
    justify-content: flex-end;
    align-items: center;
  }

  .time {
    height: 16vh;
    font-size: 80px;
    color: white;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .img-container {
    opacity: 0;
    transition: opacity 1.5s ease-in-out;
  }

  .img-container.on {
    opacity: 1;
  }

  .img-container {
    display: flex;
    justify-content: center;
    align-items: center;
    background: #000000;
    width: 100vw;
    height: 100vh;
    position: absolute;
    top: 0;
    left: 0;
    right: 100vw;
    bottom: 100vh;
  }

  .img-container img {
    height: 100vh;
    max-width: 100vw;
  }

</style>
