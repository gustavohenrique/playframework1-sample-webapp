Ext.define('PoupaNiquel.store.Accounts', {
    extend: 'Ext.data.Store',
    model: 'PoupaNiquel.model.Account',
    
    storeId: 'Accounts',
    autoLoad: true,
    pageSize: 35,
    
    proxy: {
        type: 'rest',
        api: {
        	read : '/transactions/accounts',
        	create : '/accounts/create',
            update: '/accounts/update',
            destroy: '/accounts/delete'
        },
        reader: {
            type: 'json',
            root: 'data',
            successProperty: 'success'
        },
        writer: {
            type: 'json'
        },
        
        listeners: {
        	write: function(store, operation){
                var record = operation.records[0],
                    name = Ext.String.capitalize(operation.action),
                    verb;
                    
                if (name == 'Destroy') {
                    verb = 'Destroyed';
                } else {
                    verb = name + 'd';
                }
                Ext.example.msg(name, Ext.String.format("{0} user: {1}", verb, record.getId()));
                
            }
        }
    }
});