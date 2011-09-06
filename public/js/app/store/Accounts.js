Ext.define('PoupaNiquel.store.Accounts', {
    extend: 'Ext.data.Store',
    model: 'PoupaNiquel.model.Account',
    storeId: 'Accounts',
    alias: 'widget.storeAccounts',
    autoLoad: true,
    pageSize: 35,
    
    
//    data : [
//        {"abbr":"AL", "name":"Alabama"},
//        {"abbr":"AK", "name":"Alaska"},
//        {"abbr":"AZ", "name":"Arizona"}
//    ]
    
    
//    autoLoad: {start: 0, limit: 35},
    
    proxy: {
        type: 'ajax',
        api: {
        	read : '/transactions/accounts',
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