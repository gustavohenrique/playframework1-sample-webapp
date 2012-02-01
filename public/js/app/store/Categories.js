Ext.define('PoupaNiquel.store.Categories', {
    extend: 'Ext.data.Store',
    model: 'PoupaNiquel.model.Category',
    
    autoLoad: true,
    autoSync: false,
    pageSize: 30,
    
    proxy: Ext.create('PoupaNiquel.store.CommonProxy', {
    	model: 'Category',
    	api: {
        	read : '/categories/read',
        	create : '/categories/create',
            update: '/categories/update',
            destroy: '/categories/delete'
        },
    }),
    
});