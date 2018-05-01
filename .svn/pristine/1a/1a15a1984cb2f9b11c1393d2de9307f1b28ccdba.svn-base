$.fn.extend({
  updatecolor:function (data,returndata) {
      var target = $(this);

      console.log(target);

      for(var i =0;i<target.length;i++){
          var targettd = target[0].childNodes[1];
      $.each(data,function (index,datasource) {
          switch(datasource.o){
              case 0:
                  target.eq(1).css("color","#fff");
                  break;
              case 1:
                  target.eq(1).css("color","#000");
                  break;
              case 2:
                  target.eq(1).css("color","red");
                  break;
          }
          returndata(index,datasource.o,target);


      });

      }
  }
})