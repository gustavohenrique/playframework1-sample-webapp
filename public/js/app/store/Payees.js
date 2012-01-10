Ext.define('PoupaNiquel.store.Payees', {
    extend: 'Ext.data.Store',
    model: 'PoupaNiquel.model.Generic',
    
    storeId: 'Payees',
    autoLoad: true,
    pageSize: 1000,
    
    proxy: {
        type: 'rest',
        api: {
        	read : '/payees/read',
        	create : '/payees/create',
            update: '/payees/update',
            destroy: '/payees/delete'
        },
        
        reader: {
            type: 'json',
            root: 'data',
            successProperty: 'success'
        },
        
        writer: {
            type: 'json',
            root: 'data',
        },
    }
});