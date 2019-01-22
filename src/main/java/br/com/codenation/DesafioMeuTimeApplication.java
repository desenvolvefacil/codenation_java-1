package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.codenation.desafio.bean.*;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import java.util.Objects;
import java.util.stream.Collectors;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

    private final List<TimeBean> listaTimes = new ArrayList<>();
    private final List<JogadorBean> listaJogadores = new ArrayList<>();

    /**
     * Realiza a inclusÃ£o de um novo time.
     *
     * @param id
     * @param nome
     * @param dataCriacao
     * @param corUniformePrincipal
     * @param corUniformeSecundario
     */
    @Desafio("incluirTime")
    @Override
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) throws IdentificadorUtilizadoException {
        //verifica se jÃ¡ existe o identificado
        if (existeIdTime(id)) {
            throw new IdentificadorUtilizadoException();
        } else {
            //insere o novo time
            TimeBean timeBean = new TimeBean(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);

            listaTimes.add(timeBean);

        }
    }

    /**
     * Realiza a inclusÃ£o de um novo jogador.
     *
     * @param id
     * @param idTime
     * @param nome
     * @param dataNascimento
     * @param nivelHabilidade
     * @param salario
     */
    @Desafio("incluirJogador")
    @Override
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) throws TimeNaoEncontradoException, IdentificadorUtilizadoException {
        if (!existeIdTime(idTime)) {
            throw new TimeNaoEncontradoException();
        } else if (existeIdJogador(id)) {
            throw new IdentificadorUtilizadoException();
        } else {
            //insere o novo jogador
            JogadorBean jogadorBean = new JogadorBean(id, idTime, nome, dataNascimento, nivelHabilidade, salario);

            listaJogadores.add(jogadorBean);
        }
    }

    /**
     * Define um jogador como capitao do seu time. Um time deve ter apenas um
     * capitao, por tanto o capitao anterior voltar¡ a ser apenas jogador.
     *
     * @param idJogador
     */
    @Desafio("definirCapitao")
    @Override
    public void definirCapitao(Long idJogador) throws JogadorNaoEncontradoException {
        JogadorBean jogadorBean = buscarJogador(idJogador);

        if (jogadorBean != null) {

            TimeBean timeBean = buscarTime(jogadorBean.getIdTime());

            timeBean.setCapitao(idJogador);

        } else {
            throw new JogadorNaoEncontradoException();
        }

    }

    /**
     * Mostra o identificador do capitao do time.
     *
     * @param idTime
     * @return
     */
    @Desafio("buscarCapitaoDoTime")
    @Override
    public Long buscarCapitaoDoTime(Long idTime) throws CapitaoNaoInformadoException, TimeNaoEncontradoException {

        TimeBean timeBean = buscarTime(idTime);

        if (timeBean != null) {
            if (timeBean.getCapitao() != null) {
                return timeBean.getCapitao();
            } else {
                throw new CapitaoNaoInformadoException();
            }
        } else {
            throw new TimeNaoEncontradoException();
        }

    }

    /**
     * Retorna o nome do jogador.
     *
     * @param idJogador
     * @return
     */
    @Desafio("buscarNomeJogador")
    @Override
    public String buscarNomeJogador(Long idJogador) throws JogadorNaoEncontradoException {
        JogadorBean jogadorBean = buscarJogador(idJogador);

        if (jogadorBean != null) {
            return jogadorBean.getNome();
        } else {
            throw new JogadorNaoEncontradoException();
        }

    }

    /**
     * Retorna o nome do time.
     *
     * @param idTime
     * @return
     */
    @Desafio("buscarNomeTime")
    @Override
    public String buscarNomeTime(Long idTime) throws TimeNaoEncontradoException {
        TimeBean timeBean = buscarTime(idTime);

        if (timeBean != null) {
            return timeBean.getNome();
        } else {
            throw new TimeNaoEncontradoException();
        }

    }

    /**
     * Retorna a lista com o identificador de todos os jogadores do time,
     * ordenada pelo id.
     *
     * @param idTime
     * @return
     */
    @Desafio("buscarJogadoresDoTime")
    @Override
    public List<Long> buscarJogadoresDoTime(Long idTime) throws TimeNaoEncontradoException {

        if (existeIdTime(idTime)) {
            List<Long> listaIds = listaJogadores.stream().filter(j -> Objects.equals(j.getIdTime(), idTime)).map(JogadorBean::getId).collect(Collectors.toList());

            //ordena a lista
            Collections.sort(listaIds);

            return listaIds;

        } else {
            throw new TimeNaoEncontradoException();
        }

    }

    /**
     * Retorna o identificador do melhor jogador do time.
     *
     * @param idTime
     * @return
     */
    @Desafio("buscarMelhorJogadorDoTime")
    @Override
    public Long buscarMelhorJogadorDoTime(Long idTime) throws TimeNaoEncontradoException {
        if (existeIdTime(idTime)) {

            Long idJogadorMax = listaJogadores.stream().filter(j -> Objects.equals(j.getIdTime(), idTime)).max(Comparator.comparing(JogadorBean::getNivelHabilidade)).map(JogadorBean::getId).orElse(null);

            return idJogadorMax;

        } else {
            throw new TimeNaoEncontradoException();
        }
    }

    /**
     * Retorna o identificador do jogador mais velho do time. Usar o menor
     * identificador como criterio de desempate.
     *
     * @param idTime
     * @return
     */
    @Desafio("buscarJogadorMaisVelho")
    @Override
    public Long buscarJogadorMaisVelho(Long idTime) throws TimeNaoEncontradoException {
        if (existeIdTime(idTime)) {

            //busca os jogadores do time
            List<JogadorBean> listaAux = listaJogadores.stream().filter(j -> Objects.equals(j.getIdTime(), idTime)).collect(Collectors.toList());

            if (listaAux != null && listaAux.size() > 0) {

                JogadorBean  maisVelho = Collections.max(listaAux, 
				Comparator.comparing((JogadorBean j) -> j.getDataNascimento())
				.thenComparing(j->j.getId()).reversed());
		
		return maisVelho.getId();
                
            } else {
                return null;
            }

        } else {
            throw new TimeNaoEncontradoException();
        }
    }

    /**
     * Retorna uma lista com o identificador de todos os times cadastrado,
     * ordenada pelo identificador. Retornar uma lista vazia caso nÃ£o encontre
     * times cadastrados.
     *
     * @return
     */
    @Desafio("buscarTimes")
    @Override
    public List<Long> buscarTimes() {
        List<Long> listaIds = listaTimes.stream().map(TimeBean::getId).collect(Collectors.toList());

        //ordena a lista
        Collections.sort(listaIds);

        return listaIds;
    }

    /**
     * Retorna o identificador do jogador com maior salario do time. Usar o
     * menor identificador como criterio de desempate.
     *
     * @param idTime
     * @return
     */
    @Desafio("buscarJogadorMaiorSalario")
    @Override
    public Long buscarJogadorMaiorSalario(Long idTime) throws TimeNaoEncontradoException {
        if (existeIdTime(idTime)) {

            //busca os jogadores do time
            List<JogadorBean> listaAux = listaJogadores.stream().filter(j -> Objects.equals(j.getIdTime(), idTime)).collect(Collectors.toList());

            //verifica qual a idade do mais velho
            BigDecimal salarioMax = listaAux.stream().max(Comparator.comparing(JogadorBean::getSalario)).map(JogadorBean::getSalario).orElse(null);

            //pega o menor id com esta idade
            Long idJogadorMax = listaAux.stream().filter(j -> j.getSalario().equals(salarioMax)).min(Comparator.comparing(JogadorBean::getId)).map(JogadorBean::getId).orElse(null);

            return idJogadorMax;

        } else {
            throw new TimeNaoEncontradoException();
        }
    }

    /**
     * Retorna o salario do jogador.
     *
     * @param idJogador
     * @return
     */
    @Desafio("buscarSalarioDoJogador")
    @Override
    public BigDecimal buscarSalarioDoJogador(Long idJogador) throws JogadorNaoEncontradoException {

        JogadorBean jogadorBean = buscarJogador(idJogador);

        if (jogadorBean != null) {
            return jogadorBean.getSalario();
        } else {
            throw new JogadorNaoEncontradoException();
        }
    }

    /**
     * Retorna uma lista com o identificador dos top melhores jogadores,
     * utilizar o menor identificador como criterio de desempate.
     *
     * @param top
     * @return
     */
    @Desafio("buscarTopJogadores")
    @Override
    public List<Long> buscarTopJogadores(Integer top) {

        List<Long> listaTops = listaJogadores.stream().sorted(
                (s1, s2) -> Integer.compare(s2.getNivelHabilidade(), s1.getNivelHabilidade())
        ).limit(top).map(JogadorBean::getId).collect(Collectors.toList());

        return listaTops;
    }

    /**
     * Retorna a cor da camisa do time adversario. Caso a cor principal do time
     * da casa seja igual a cor principal do time de fora, retornar cor
     * secundaria do time de fora. Caso a cor principal do time da casa seja
     * diferente da cor principal do time de fora, retornar cor principal do
     * time de fora.
     *
     * @param timeDaCasa
     * @param timeDeFora
     * @return
     */
    @Desafio("buscarCorCamisaTimeDeFora")
    @Override
    public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) throws TimeNaoEncontradoException {
        TimeBean timeBeanCasa = buscarTime(timeDaCasa);

        TimeBean timeBeanFora = buscarTime(timeDeFora);

        if(timeBeanCasa==null || timeBeanFora==null){
            throw new TimeNaoEncontradoException();
        }
        
        return (timeBeanCasa.getCorUniformePrincipal().equals(timeBeanFora.getCorUniformePrincipal())) ? timeBeanFora.getCorUniformeSecundario() : timeBeanFora.getCorUniformePrincipal();

    }

    
    /**
     * *
     * Verifica se Existe id de time cadastrado
     *
     * @param idTime
     * @return
     */
    private boolean existeIdTime(Long idTime) {
        //verifica se jÃ¡ existe o identificado
        return listaTimes.stream().filter(t -> Objects.equals(t.getId(), idTime)).toArray().length > 0;
    }

    /**
     * *
     * Verifica se existe id de jogador cadastrado
     *
     * @param idJogador
     * @return
     */
    private boolean existeIdJogador(Long idJogador) {
        //verifica se jÃ¡ existe o identificado
        return listaJogadores.stream().filter(t -> Objects.equals(t.getId(), idJogador)).toArray().length > 0;
    }

    /**
     * Busca um Jogador pelo id
     *
     * @param idJogador
     * @return
     */
    private JogadorBean buscarJogador(Long idJogador) {
        return listaJogadores.stream().filter(t -> Objects.equals(t.getId(), idJogador)).findFirst().orElse(null);
    }

    /**
     * Busca um time pelo id
     *
     * @param idTime
     * @return
     */
    private TimeBean buscarTime(Long idTime) {
        return listaTimes.stream().filter(t -> Objects.equals(t.getId(), idTime)).findFirst().orElse(null);
    }
}
