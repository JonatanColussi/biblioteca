	<script src="assets/js/jquery-3.2.1.min.js"></script>
	<script src="assets/js/popper.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
	<script>
		$(document).ready(function(){
			$("table").DataTable({
				language:{
					url: '//cdn.datatables.net/plug-ins/1.10.16/i18n/Portuguese-Brasil.json'
				}
			});
			
			$(".nav li[data-controller]").each(function(index, el){
				if (location.pathname.search($(this).data('controller')) >= 0) {
					$(this).addClass('active');
				}
			});
		});
	</script>
</body>
</html>