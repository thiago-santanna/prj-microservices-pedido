package com.tsswebapps.processador_services.service;

import com.tsswebapps.processador_services.entity.ItemPedido;
import com.tsswebapps.processador_services.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void save(List<ItemPedido> itens) {
        itens.forEach(item -> {
            produtoRepository.save(item.getProduto());
        });
    }
}
