/**
 * Created by zhuhejun on 2016/6/22.
 */
var player = videojs('really-cool-video', {
	"width": 640,
	"height": 480,
	"controls":true,
	"poster": "/mp4/ladies.jpg",
}, function() {
	console.log('Good to go!');

	player.src("/mp4/Introducing the Nexus 6P.mp4");
	// player.load("http://localhost.jd.com/mp4/Introducing the Nexus 6P.mp4");


	// this.play(); // if you don't trust autoplay for some reason

	// How about an event listener?
	this.on('ended', function() {
		console.log('awww...over so soon?');


		var url = "/mp4/iphone6s-feature-hk-20150909_1280x720h.mp4"
		player.src(url);  //重置video的src
		player.poster("/mp4/hero_silver_s_large.jpg")
		player.load(url);  //使video重新加载

	});
});



function send(param,callback){

	/**
	 * 逻辑代码
	 */







	callback(data);

}


send({
	"aa":1,
	"bbb":2


},function(data){

})

