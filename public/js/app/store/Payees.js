Ext.define('PoupaNiquel.store.Payees', {
    extend: 'Ext.data.Store',
    model: 'PoupaNiquel.model.Generic',
    
    storeId: 'Payees',
    autoLoad: true,
    pageSize: 1000,
    
    proxy: {
        type: 'ajax',
        api: {
        	read : '/transactions/payees',
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