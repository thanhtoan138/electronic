app.controller("chart-ctrl",
	window.onload = function () {
	var dps = [];
	function addDataPointsAndRender(chartdata) {
     
        for (let i = 0; i < 12; i++) {
		 let a = new Date(chartdata[i][1]);
		 let b = parseInt(chartdata[i][0]);
		   dps.push({
            x: a,
            y: b
        });
 		}
       
    }
    
    var MonthlyPriceByYear = [];
    function chartMonthlyPriceByYear(PriceChartByMonth) {
     
        for (let i = 0; i < 12; i++) {
		 let a = new Date(PriceChartByMonth[i][1]);
		 let b = parseInt(PriceChartByMonth[i][0]);
		 
		   MonthlyPriceByYear.push({
            x: a,
            y: b
			
        });
 		}
       
    }
    
    var dpsQuantityChartBrand =[];
    function chartQuantityChartBrand(QuantityChartBrand) {
     
        for (let i = 0; i < QuantityChartBrand.length; i++) {
		 let a = QuantityChartBrand[i][1];
		 let b = parseInt(QuantityChartBrand[i][0]);
		   dpsQuantityChartBrand.push({
            label: a,
            y: b
        });
 		}
       
    }
    
     var dpsQuantityChartCategory =[];
    function chartQuantityChartCategory(QuantityChartCategory) {
     
        for (let i = 0; i < QuantityChartCategory.length; i++) {
		 let a = QuantityChartCategory[i][1];
		 let b = parseInt(QuantityChartCategory[i][0]);
		   dpsQuantityChartCategory.push({
            label: a,
            y: b
        });
 		}
       
    }
    
		var apiUrl = '/rest/admin/chartMonthlySalesByYear';
		$.ajax({
			type: "GET",
			dataType: "json",
			url: apiUrl,
			async: false,
			success: function(chartdata) {
				addDataPointsAndRender(chartdata);
			},
			error: function(e) {
				alert("Error, please try again!");
			}
		});
		var apiUrl = '/rest/admin/chartMonthlyPriceByYear';
		$.ajax({
			type: "GET",
			dataType: "json",
			url: apiUrl,
			async: false,
			success: function(PriceChartByMonth) {
				chartMonthlyPriceByYear(PriceChartByMonth);
			},
			error: function(e) {
				alert("Error, please try again!");
			}
		});

		var apiUrl = '/rest/admin/quantityChartBrand';
		$.ajax({
			type: "GET",
			dataType: "json",
			url: apiUrl,
			async: false,
			success: function(QuantityChartBrand) {
				chartQuantityChartBrand(QuantityChartBrand);
			},
			error: function(e) {
				alert("Error, please try again!");
			}
		});

		var apiUrl = '/rest/admin/quantityChartCategory';
		$.ajax({
			type: "GET",
			dataType: "json",
			url: apiUrl,
			async: false,
			success: function(QuantityChartCategory) {
				chartQuantityChartCategory(QuantityChartCategory);
			},
			error: function(e) {
				alert("Error, please try again!");
			}
		});

     
       	
    var chart = new CanvasJS.Chart("chartContainer",
    {
      title: {
        text: "No. of Product Sale per Month",
        fontSize: 25
      },
      axisX:{
        valueFormatString: "MMM" ,
        interval: 1,
        intervalType: "month"

      },
      axisY: {
        title: "Quantity"
      },
      data: [
      {
        indexLabelFontColor: "green",
        type: "area",
        dataPoints: dps
      }
      ]
    });
    
   var chart1 = new CanvasJS.Chart("chartContainer1", {
	animationEnabled: true,
	title:{
		text: " Quantity Chart By Brand",
		horizontalAlign: "left"
	},
	data: [{
		type: "doughnut",
		startAngle: 60,
		//innerRadius: 60,
		indexLabelFontSize: 17,
		indexLabel: "{label} - #percent%",
		toolTipContent: "<b>{label}:</b> {y} (#percent%)",
		dataPoints: dpsQuantityChartBrand
		/*[
			{ y: 67, label: "Inbox" },
			{ y: 28, label: "Archives" },
			{ y: 10, label: "Labels" },
			{ y: 7, label: "Drafts"},
			{ y: 15, label: "Trash"},
			{ y: 6, label: "Spam"}
		]*/
	}]
});
    
    var chart2 = new CanvasJS.Chart("chartContainer2", {
	animationEnabled: true,
	title: {
		text: "Total In Inventory"
	},
	data: [{
		type: "pie",
		startAngle: 300,
	/*	yValueFormatString: "##0.00\"%\"",*/
		indexLabel: "{label} {y}",
		dataPoints: dpsQuantityChartCategory
		/*[
			{y: 79.45, label: "Google"},
			{y: 7.31, label: "Bing"},
			{y: 7.06, label: "Baidu"},
			{y: 4.91, label: "Yahoo"},
			{y: 1.26, label: "Others"}
		]*/
	}]
});
    
    var chart3 = new CanvasJS.Chart("chartContainer3",
    {
    animationEnabled: true,
	theme: "light2",
	title:{
		text: "Revenue By Month"
	},
	data: [{        
		type: "line",
      	indexLabelFontSize: 16,
		dataPoints:MonthlyPriceByYear
		 /*[
			{ y: 450 },
			{ y: 414},
			{ y: 520, indexLabel: "\u2191 highest",markerColor: "red", markerType: "triangle" },
			{ y: 460 },
			{ y: 450 },
			{ y: 500 },
			{ y: 480 },
			{ y: 480 },
			{ y: 410 , indexLabel: "\u2193 lowest",markerColor: "DarkSlateGrey", markerType: "cross" },
			{ y: 500 },
			{ y: 480 },
			{ y: 510 }
		]*/
	}]
});
	
    chart.render();
    chart1.render();	
    chart2.render();
    chart3.render();
   }
   
);