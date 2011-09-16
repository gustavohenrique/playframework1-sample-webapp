Ext.define('PoupaNiquel.store.Transactions', {
    extend: 'Ext.data.Store',
    model: 'PoupaNiquel.model.Transaction',
    
    storeId: 'Transactions',
    autoLoad: true,
    pageSize: 31,
    
    proxy: {
        type: 'ajax',
        api: {
        	read : '/transactions/filter',
        },
        reader: {
            type: 'json',
            root: 'data',
            successProperty: 'success'
        },
    
        listeners: {
            exception: function(proxy, response, operation){
                //alert(operation.getError());
            }
        }
    }
});