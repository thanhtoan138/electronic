app.controller("order-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.ship = [];
	$scope.form = {};
    $scope.pay = {};
	$scope.initialize = function() {       
		//load products
		$http.get("/rest/orders").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate)
			})
		});
		//load ship status 
		$http.get("/rest/shipstatus").then(resp => {
			$scope.ship = resp.data;
		});
        //load payment status
		$http.get("/rest/paystatus").then(resp => {
			$scope.pay = resp.data;
		});
		
	}
	//khởi đầu 
	$scope.initialize();
	
	//hiển thị lên form
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
	}
	//cập nhật sản phẩm
	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/orders/${item.id}`,item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			alert("Cập nhật sản phẩm thành công!");
			$scope.initialize();
		}).catch(error => {
			alert("Lỗi thêm mới sản phẩm!");
			console.log("Error", error);
		})
	}
	$scope.delete = function(item) {
		$http.delete(`/rest/orders/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index,1);
			alert("Xóa sản phẩm thành công!");
		}).catch(error => {
			alert("Phải xóa bảng Order Details trước!");
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