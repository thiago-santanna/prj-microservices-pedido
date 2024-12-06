package com.tsswebapps.processador_services.service;

import com.tsswebapps.processador_services.entity.ItemPedido;
import com.tsswebapps.processador_services.entity.Pedido;
import com.tsswebapps.processador_services.repository.ItemPedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }


    public List<ItemPedido> save(List<ItemPedido> itens) {
        return itemPedidoRepository.saveAll(itens);
    }

    public void save(ItemPedido itemPedido) {
        itemPedidoRepository.save(itemPedido);
    }

    public void updateItemIdPedido(List<ItemPedido> itemPedidos, Pedido pedido) {
        itemPedidos.forEach(item -> {
            item.setPedido(pedido);
            itemPedidoRepository.save(item);
        });
    }
}
