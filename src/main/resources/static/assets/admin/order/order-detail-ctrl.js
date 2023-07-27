app.controller("order-detail-ctrl", function($scope,$http) {
    $scope.items = [];
    $scope.ship=[];
    $scope.pay=[];
    $scope.form={};
    $scope.init = function(){       
        $http.get("/rest/order-detail").then(resp=>{
            $scope.items=resp.data;
            $scope.items.forEach(item => {
                item.createDate = new Date (item.createDate)
            });
        });
        $http.get("/rest/shipstatus").then(resp=>{
            $scope.ship=resp.data;
        });
        //load payment status
		$http.get("/rest/paystatus").then(resp => {
			$scope.pay = resp.data;
		});
    }    
    $scope.init();
    $scope.edit = function(item){
        $scope.form = angular.copy(item);
    }
    $scope.update = function(it){   
        var item = angular.copy(it);  
        $http.put(`/rest/order-detail/${item.id}`,item).then(resp=>{
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items[index] = item;         
        }).catch(error => {
			console.log("Error", error);
		})
    }
    //xóa sản phẩm
	// $scope.delete = function(product_id,order_id) {
	// 	$http.delete(`/rest/order-detail/${product_id}/${order_id}`).then(resp => {
	// 		var index = $scope.items.findIndex(p => p.id == item.id);
	// 		$scope.items.splice(index,1);
	// 		alert("Xóa sản phẩm thành công!");
	// 	}).catch(error => {
	// 		alert("Lỗi sản phẩm!");
	// 		console.log("Error", error);
	// 	})
	// }
	$scope.delete = function(item) {
		$http.delete(`/rest/order-detail/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index,1);
			alert("Xóa sản phẩm thành công!");
		}).catch(error => {
			alert("Lỗi sản phẩm!");
			console.log("Error", error);
		})
	}
	$scope.pager = {
		page: 0,
		size: 6,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count(){
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if(this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if(this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}
	}

});