Ext.define('PoupaNiquel.store.Transactions', {
    extend: 'Ext.data.Store',
    model: 'PoupaNiquel.model.Transaction',
    
    autoLoad: false,
    pageSize: 10,
    remoteFilter: true,
//    remoteSort: true,
    
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