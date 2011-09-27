Ext.define('PoupaNiquel.store.Transactions', {
    extend: 'Ext.data.Store',
    model: 'PoupaNiquel.model.Transaction',
    
    storeId: 'Transactions',
    autoLoad: false,
    pageSize: 10,
    
    
    proxy: {
        type: 'ajax',
//      limitParam: 'limitPage',
//    	pageParam: 'startPage',
    	
        api: {
        	read : '/transactions/filter',
        },
        reader: {
            type: 'json',
            root: 'data',
            successProperty: 'success',
            totalProperty: 'total'
        },
    
        listeners: {
            exception: function(proxy, response, operation){
                //alert(operation.getError());
            }
        }
    }
});