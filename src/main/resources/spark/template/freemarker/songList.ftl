<#if hasNoSongs??>
    <div class="starter-template">
        <h1>${hasNoSongs}</h1>
    </div>
<#else>

<br><br>
    <table class="table panel">
  <tr>
    <thead>
                      
      <th>Título </th> 
      <th>Autor</th>
      <th>Duración</th>
      <th>Menu</th>
                      
    </thead>

  </tr>  
        <#list songs as song>
<tr>
            <td>${song.getName()}</td>
<td>${song.getLength()}</td>
            <td>${song.getAuthor()}</td>
            

<td>
      
        <center><a href="/song/update/${song.id}" class="btn btn-success">Editar</a>
        <a href="/song/delete/${song.id}" class="btn btn-warning">Borrar</a></center>
     
</td>
</tr>
        </#list>
    </table>
</#if>